
import React, {Component} from 'react';

import {Modal, ModalBody, ModalFooter, ModalHeader,} from "reactstrap";
import {deleteWarehouse,  getWarehouses, saveWarehouse} from "../redux/actions/AppAction";
import {connect} from "react-redux";
import './css/style.css';


class Warehouse extends Component {
    componentDidMount() {
        this.props.dispatch(getWarehouses())
        this.props.dispatch(getP())
    }

    render() {
        const {warehouses, products, showModal, deleteModal, currentItem, dispatch} = this.props;

        console.log(warehouses.length)
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
        const saveWarehouses = (e) => {

            let obj = {}
            let product_id = document.getElementById("product_id").value;
            let amount = document.getElementById("amount").value;
            let arrived_date = document.getElementById("arrived_date").value;
            let made_at = document.getElementById("made_at").value;
            let expire_date = document.getElementById("expire_date").value;
            let price = document.getElementById("price").value;
            let sell_price = document.getElementById("sell_price").value;
            let leftover = document.getElementById("leftover").value;
            let id = currentItem.id ? currentItem.id : null;
            if (currentItem.id) {
                obj = {product_id, amount, arrived_date, made_at, expire_date, price, sell_price, leftover, id}

            } else {
                obj = {obj}
            }


            this.props.dispatch(saveWarehouse(obj))
        }
        const deleteWarehouses = () => {
            this.props.dispatch(deleteWarehouse(currentItem
            ))
        }

        return (
            <div className="container">
                <h2 className="text-center">Warehouse List</h2>

                <button className="custom-btn btn-2" onClick={() => openModal('')}>Add</button>
                <div style={{display: 'flex'}}>
                    {warehouses.length != null ?
                        warehouses.map((item, i) =>
                            <div className="card">
                                <div className="card-body">
                                    <h5 className="card-title">{item.name}</h5>
                                    <h4>Connected Warehouse: {item.warehouseId}</h4><br/>
                                    <button className="custom-btn btn-13" onClick={() => openModal(item)}>Edit</button>
                                    {' '}
                                    <button className="custom-btn btn-12"
                                            onClick={() => openDeleteModal(item)}>Delete
                                    </button>
                                </div>
                            </div>
                        )
                        : "Malumot mavjud emas"
                    }
                </div>

                <Modal isOpen={showModal}>
                    <ModalHeader>{currentItem.id ? "Edit warehouse" : "Add warehouse"}</ModalHeader>
                    <ModalBody>
                        <div className="warehouse">
                            <select name="product" className="form-control" id="product">
                                <option value="0"  selected={true} disabled={true}>selected product</option>
                                {products.map((item, i)=>(
                                    <option value={item.id}>{item.name}</option>
                                ))}
                            </select>
                            <span className="highlight"></span>
                            <span className="bar"></span>
                            <label>product</label>
                            <input type="number" required id="amount" name="amount"
                                   defaultValue={currentItem.name}/>
                            <input type="date" required id="arrived_date" name="arrived_date"
                                   defaultValue={currentItem.name}/>
                             <input type="date" required id="made_at" name="made_at"
                                   defaultValue={currentItem.name}/>
                            <input type="date" required id="expire_date" name="expire_date"
                                   defaultValue={currentItem.name}/>

                        </div>



                    </ModalBody>
                    <ModalFooter>
                        <button className="custom-btn btn-12" onClick={() => openModal('')}>Cancel</button>
                        <button className="custom-btn btn-11" onClick={saveWarehouses}>Save
                            <div className="dot"></div>
                        </button>
                    </ModalFooter>
                </Modal>
                <Modal isOpen={deleteModal}>
                    <ModalHeader>Delete Warehouse</ModalHeader>
                    <ModalBody>{"Are you sure " + currentItem.name}</ModalBody>
                    <ModalFooter>
                        <button className="custom-btn btn-12" onClick={() => openDeleteModal('')}>Cancel</button>
                        <button className="custom-btn btn-11" onClick={deleteWarehouses}>Delete
                        </button>
                    </ModalFooter>
                </Modal>
            </div>

        );
    }
}

Warehouse.propTypes = {};

export default connect(
    ({app: {warehouses, showModal, deleteModal, currentItem}}) =>
        ({warehouses, showModal, deleteModal, currentItem}))
(Warehouse);