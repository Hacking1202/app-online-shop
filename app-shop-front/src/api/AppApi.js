import HttpClient from "../utils/HttpClient";
import {api} from "./api";
//Datares uchun
export const getMakers = () => {
    return HttpClient.doGet(api.maker);
}

export const addMaker = (data) => {
    return HttpClient.doPost(api.maker, data);
}
export const updateMaker = (data) => {
    return HttpClient.doPut(api.maker + "/" + data.id, data);
}
export const delMaker = (data) => {
    return HttpClient.doDelete(api.maker + "/" + data.id);
}
//Category
export const getCategorys = () => {
    return HttpClient.doGet(api.category+"/list");
}

export const addCategory = (data) => {
        return HttpClient.doPost(api.category, data);
}
export const updateCategory = (data) => {
    return HttpClient.doPut(api.category + "/" + data.id, data);
}
export const delCategory = (data) => {
    console.log(data)
    return HttpClient.doDelete(api.category + "/" + data.id);
}