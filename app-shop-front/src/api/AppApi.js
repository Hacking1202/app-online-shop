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
//Product
export const getProducts = () => {
    return HttpClient.doGet(api.product+"/list");
}

export const addProduct = (data) => {
    alert("api")
    return HttpClient.doPost(api.product, data);
}
export const updateProduct = (data) => {
    return HttpClient.doPut(api.product + "/" + data.id, data);
}
export const delProduct = (data) => {
    console.log(data)
    return HttpClient.doDelete(api.product + "/" + data.id);
}
//Detail
export const getDetails = () => {
    return HttpClient.doGet(api.detail);
}

export const addDetail = (data) => {
    return HttpClient.doPost(api.detail, data);

}
export const updateDetail = (data) => {
    return HttpClient.doPut(api.detail + "/" + data.id, data);
}
export const delDetail = (data) => {
    return HttpClient.doDelete(api.detail + "/" + data.id);
}
//Measure
export const getMeasures = () => {
    return HttpClient.doGet(api.measure);
}

export const addMeasure = (data) => {
    return HttpClient.doPost(api.measure, data);
}
export const updateMeasure = (data) => {
    return HttpClient.doPut(api.measure + "/" + data.id, data);
}
export const delMeasure = (data) => {
    return HttpClient.doDelete(api.measure + "/" + data.id);
}
