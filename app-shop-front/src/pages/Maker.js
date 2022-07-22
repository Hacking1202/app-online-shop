import React, {Component} from 'react';
import {Button, Input, Modal, ModalBody, ModalFooter, ModalHeader, Table} from "reactstrap";
import {deleteMaker, getMaker, saveMaker} from "../redux/actions/AppAction";
import {connect} from "react-redux";


class Maker extends Component {
    componentDidMount() {
        this.props.dispatch(getMaker())
    }

    render() {
        const {makers, showModal, deleteModal, currentItem, dispatch} = this.props;

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

        const saveMakers = (e) => {
            let obj = {}
            let name = document.getElementById("name").value;
            let id = currentItem.id ? currentItem.id : null;

            if (currentItem.id) {
                obj = {id: id, name: name}
            } else {
                obj = {name}
            }
            this.props.dispatch(saveMaker(obj))
        }
        const deleteMakers = (e) => {
            this.props.dispatch(deleteMaker(currentItem))
        }

        return (
            <div className="container">
                <div>
                    <h2 className="text-center">Maker List</h2>
                    <Button className="btn btn-primary" onClick={() => openModal('')}>Add</Button>
                    <Table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th colSpan='2'>Action</th>
                        </tr>
                        </thead>
                        {makers.length != null ?
                            makers.map((item, i) =>
                                <tbody>
                                <tr>
                                    <td>{i + 1}</td>
                                    <td>{item.name}</td>
                                    <td><Button color="warning" outline onClick={() => openModal(item)}>Edit</Button>
                                    </td>
                                    <td><Button color="danger" outline
                                                onClick={() => openDeleteModal(item)}>Delete</Button>
                                    </td>
                                </tr>
                                </tbody>
                            )
                            : " Malumot mavjud emas"
                        }
                    </Table>

                </div>
                <Modal isOpen={showModal}>
                    <ModalHeader>{currentItem.id ? "Edit maker" : "Add maker"}</ModalHeader>
                    <ModalBody>
                        <Input type="text" id="name" name="name" defaultValue={currentItem.id ? currentItem.name : " "}
                               placeholder="Enter maker name" required={true}/>
                    </ModalBody>
                    <ModalFooter>
                        <Button onClick={() => openModal('')}>Cancel</Button>
                        <Button color="primary" onClick={saveMakers}>Save</Button>
                    </ModalFooter>
                </Modal>
                <Modal isOpen={deleteModal}>
                    <ModalHeader>Delete Maker</ModalHeader>
                    <ModalBody>{"Are you sure " + currentItem.name}</ModalBody>
                    <ModalFooter>
                        <Button onClick={() => openDeleteModal('')}>Cancel</Button>
                        <Button color="danger" onClick={deleteMakers}>Delete</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}

Maker.propTypes = {};

// const mapDispatchToProps={
//     getMaker
// }
//
// const mapStateToProps=state=>({
//     makers:state.app.makers,
//     ketmon: state.app.ketmon
// })

// export default connect(mapStateToProps, mapDispatchToProps)(Maker)


export default connect(
    ({app: {makers, showModal, deleteModal, currentItem}}) =>
        ({makers, showModal, deleteModal, currentItem}))
(Maker);