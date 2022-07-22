import React, {Component} from 'react';

import {Modal, ModalBody, ModalFooter, ModalHeader,} from "reactstrap";
import {deleteCategory, getCategory, saveCategory} from "../redux/actions/AppAction";
import {connect} from "react-redux";
import './css/style.css';


class Category extends Component {
    componentDidMount() {
        this.props.dispatch(getCategory())
    }

    render() {
        const {categorys, showModal, deleteModal, currentItem, dispatch} = this.props;

        console.log(categorys.length)
        const openModal = (item) => {
            dispatch({
                type: 'updateState',
                payload: {
                    showModal: !showModal,
                    currentItem: item
                }
            })
        };

        const openDeleteModal = (item) => {
            dispatch({
                type: 'updateState',
                payload: {
                    deleteModal: !deleteModal,
                    currentItem: item
                }
            })
        };
        const saveCategorys = (e) => {

            let obj = {}
            let name = document.getElementById("name").value;
            let categoryId = document.getElementById("categoryId").value;
            let id = currentItem.id ? currentItem.id : null;
            if (currentItem.id) {
                obj = {id, name, categoryId}
            } else {
                obj = {name, categoryId}
            }


            this.props.dispatch(saveCategory(obj))
        }
        const deleteCategorys = () => {
            this.props.dispatch(deleteCategory(currentItem
            ))
        }

        return (
            <div className="container">
                <h2 className="text-center">Category List</h2>

                <button className="custom-btn btn-2" onClick={() => openModal('')}>Add</button>
                <div style={{display: 'flex'}}>
                    {categorys.length != null ?
                        categorys.map((item, i) =>
                            <div id="card">
                                <div className="card-body">
                                    <h1 className="card-title"><b>{item.name}</b></h1>
                                    <div className="buttons">
                                        <button className="custom-btn btn-13" onClick={() => openModal(item)}>Edit
                                        </button>{' '}<button className="custom-btn btn-12"onClick={() => openDeleteModal(item)}>Delete</button>
                                    </div>
                                </div>
                            </div>
                        )
                        : "Malumot mavjud emas"
                    }
                </div>

                <Modal isOpen={showModal}>
                    <ModalHeader>{currentItem.id ? "Edit category" : "Add category"}</ModalHeader>
                    <ModalBody>
                        <div className="group">
                            <input type="text" required id="name" name="name"
                                   defaultValue={currentItem.name}/>
                            <span className="highlight"></span>
                            <span className="bar"></span>
                            <label>Name</label>
                        </div>

                        <select name="categoryId" id="categoryId" disabled={categorys.length === 0}>
                            <option value={null}>Selected Category</option>
                            {categorys.map((item, i) => <option value={item.id}>{item.name}</option>)}
                        </select>


                    </ModalBody>
                    <ModalFooter>
                        <button className="custom-btn btn-12" onClick={() => openModal('')}>Cancel</button>
                        <button className="custom-btn btn-11" onClick={saveCategorys}>Save
                            <div className="dot"></div>
                        </button>
                    </ModalFooter>
                </Modal>
                <Modal isOpen={deleteModal}>
                    <ModalHeader>Delete Category</ModalHeader>
                    <ModalBody>{"Are you sure " + currentItem.name}</ModalBody>
                    <ModalFooter>
                        <button className="custom-btn btn-12" onClick={() => openDeleteModal('')}>Cancel</button>
                        <button className="custom-btn btn-11" onClick={deleteCategorys}>Delete
                        </button>
                    </ModalFooter>
                </Modal>
            </div>

        );
    }
}

Category.propTypes = {};

export default connect(
    ({app: {categorys, showModal, deleteModal, currentItem}}) =>
        ({categorys, showModal, deleteModal, currentItem}))
(Category);