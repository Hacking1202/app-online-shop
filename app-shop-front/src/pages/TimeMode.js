import React, {Component} from 'react';
import {Button, Input, Modal, ModalBody, ModalFooter, ModalHeader, Table} from "reactstrap";
import {deleteTimeMode, getTimeMode, saveTimeMode} from "../redux/actions/AppAction";
import {connect} from "react-redux";


class TimeMode extends Component {
    componentDidMount() {
        this.props.dispatch(getTimeMode())
    }

    render() {
        const {timeModes, showModal, deleteModal, currentItem, dispatch} = this.props;

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

        const saveTimeModes = (e) => {
            let obj = {}
            let name = document.getElementById("name").value;
            let id = currentItem.id ? currentItem.id : null;

            if (currentItem.id) {
                obj = {id: id, name: name}
            } else {
                obj = {name}
            }
            this.props.dispatch(saveTimeMode(obj))
        }
        const deleteTimeModes = (e) => {
            this.props.dispatch(deleteTimeMode(currentItem))
        }

        return (
            <div className="container">
                <div>
                    <h2 className="text-center">TimeMode List</h2>
                    <Button className="btn btn-primary" onClick={() => openModal('')}>Add</Button>
                    <Table>
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Name</th>
                            <th colSpan='2'>Action</th>
                        </tr>
                        </thead>
                        {timeModes.length != null ?
                            timeModes.map((item, i) =>
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
                    <ModalHeader>{currentItem.id ? "Edit timeMode" : "Add timeMode"}</ModalHeader>
                    <ModalBody>
                        <Input type="text" id="name" name="name" defaultValue={currentItem.id ? currentItem.name : " "}
                               placeholder="Enter timeMode name" required={true}/>
                    </ModalBody>
                    <ModalFooter>
                        <Button onClick={() => openModal('')}>Cancel</Button>
                        <Button color="primary" onClick={saveTimeModes}>Save</Button>
                    </ModalFooter>
                </Modal>
                <Modal isOpen={deleteModal}>
                    <ModalHeader>Delete TimeMode</ModalHeader>
                    <ModalBody>{"do you want to delete " + currentItem.name}</ModalBody>
                    <ModalFooter>
                        <Button onClick={() => openDeleteModal('')}>Cancel</Button>
                        <Button color="danger" onClick={deleteTimeModes}>Delete</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}

TimeMode.propTypes = {};

// const mapDispatchToProps={
//     getTimeMode
// }
//
// const mapStateToProps=state=>({
//     timeModes:state.app.timeModes,
//     ketmon: state.app.ketmon
// })

// export default connect(mapStateToProps, mapDispatchToProps)(TimeMode)


export default connect(
    ({app: {timeModes, showModal, deleteModal, currentItem}}) =>
        ({timeModes, showModal, deleteModal, currentItem}))
(TimeMode);