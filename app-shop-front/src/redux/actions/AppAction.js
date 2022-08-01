import {
    addCategory, addMaker, addTimeMode, addWarehouse,
    delCategory,
    delMaker, delTimeMode,
    getCategorys,
    getMakers, getTimeModes, getWarehouse,
    updateCategory,
    updateMaker, updateTimeMode, updateWarehouse,
} from "../../api/AppApi";
import * as types from "../actionTypes/AppActionTypes";
import {toast} from "react-toastify";
import {GET_MAKER_LIST} from "../actionTypes/AppActionTypes";


export const getMaker = () => (dispatch) => {
    dispatch({
        api: getMakers,
        types: [
            types.REQUEST_START,
            types.GET_MAKER_LIST,
            types.REQUEST_ERROR
        ]
    })
}

export const saveMaker = (payload) => (dispatch) => {
    dispatch({
        api: payload.id ? updateMaker : addMaker,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getMaker())
        toast.success("Successfully saved maker")
    }).catch(err => {
        toast.error("Error saving maker!");
    })
}
export const deleteMaker = (payload) => (dispatch) => {
    dispatch({
        api: delMaker,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getMaker())
        dispatch({
            type: types.REQUEST_SUCCESS
        })
        toast.success("Successfully deleted maker")
    }).catch(err => {
        toast.error("Error deleting maker!");
        alert(err);
    })
}

export const getCategory = () => (dispatch) => {
    dispatch({
        api: getCategorys,
        types: [
            types.REQUEST_START,
            types.GET_CATEGORY_LIST,
            types.REQUEST_ERROR
        ]
    })

}

export const saveCategory = (payload) => (dispatch) => {
    dispatch({
        api: payload.id ? updateCategory : addCategory,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getCategory())
        toast.success("Successfully saved category")
    }).catch(err => {
        alert(err)
        toast.error("Error saving category!");
    })
}
export const deleteCategory = (payload) => (dispatch) => {
    dispatch({
        api: delCategory,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getCategory())
        dispatch({
            type: types.REQUEST_SUCCESS
        })
        toast.success("Successfully deleted category")
    }).catch(err => {
        toast.error("Error deleting category!");
    })
}
//Warehouse
export const getWarehouses = () => (dispatch) => {
    dispatch({
        api: getWarehouse(),
        types: [
            types.REQUEST_START,
            types.GET_WAREHOUSE_LIST,
            types.REQUEST_ERROR
        ]
    })
}

export const saveWarehouse = (payload) => (dispatch) => {
    dispatch({
        api: payload.id ? updateWarehouse : addWarehouse,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getWarehouse())
        toast.success("Successfully saved warehouse")
    }).catch(err => {
        alert(err)
        toast.error("Error saving warehouse!");
    })
}
export const deleteWarehouse = (payload) => (dispatch) => {
    dispatch({
        api: deleteWarehouse(),
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getWarehouse())
        dispatch({
            type: types.REQUEST_SUCCESS
        })
        toast.success("Successfully deleted warehouse")
    }).catch(err => {
        toast.error("Error deleting warehouse!");
    })
}
//timeMode
export const getTimeMode = () => (dispatch) => {
    dispatch({
        api: getTimeModes,
        types: [
            types.REQUEST_START,
            types.GET_TIMEMODE_LIST,
            types.REQUEST_ERROR
        ]
    })
}

export const saveTimeMode = (payload) => (dispatch) => {
    dispatch({
        api: payload.id ? updateTimeMode : addTimeMode,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getTimeMode())
        toast.success("Successfully saved timeMode")
    }).catch(err => {
        toast.error("Error saving timeMode!");
    })
}
export const deleteTimeMode = (payload) => (dispatch) => {
    dispatch({
        api: delTimeMode,
        types: [
            types.REQUEST_START,
            types.REQUEST_SUCCESS,
            types.REQUEST_ERROR
        ],
        data: payload
    }).then(res => {
        dispatch({
            type: 'updateState'
        })
        dispatch(getTimeMode())
        dispatch({
            type: types.REQUEST_SUCCESS
        })
        toast.success("Successfully deleted timeMode")
    }).catch(err => {
        toast.error("Error deleting timeMode!");
        alert(err);
    })
}