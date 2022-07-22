import * as types from "../actionTypes/AppActionTypes";
import {createReducer} from "../../utils/StoreUtils";
import {GET_MAKER_LIST} from "../actionTypes/AppActionTypes";
const initState = {
    showModal: false,
    deleteModal: false,
    currentItem: "",
    categorys: [],
    makers: [],
}
const reducers = {
    [types.GET_MAKER_LIST](state, payload) {
        state.makers = payload.payload._embedded.list
    },
    [types.GET_CATEGORY_LIST](state, payload) {
        console.log(payload)
        state.categorys = payload.payload;
    },
    [types.REQUEST_SUCCESS](state, payload) {
        state.showModal = false
        state.deleteModal = false
        state.currentItem = ""
    },


    updateState(state, {payload}) {
        return {
            ...state,
            ...payload,
        };
    },
};



export default createReducer(initState, reducers);