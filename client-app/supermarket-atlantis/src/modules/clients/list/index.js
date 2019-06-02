import api from "../../../common/api/index";

const FETCH_CLIENTS = "CLIENTS/FETCH"
const SET_CLIENTS = "CLIENTS/SET"

const initialState = {
    loading: false,
    clients: []
}

export default function reducer(state = initialState, action = {}) {

    switch (action.type) {
        case FETCH_CLIENTS:
            return { ...state, loading: true };
        case SET_CLIENTS:
            return { ...state, loading: false, clients: action.payload };
        default:
            return state;
    }
}

export const getClients = () => (dispatch) => {
    dispatch({ type: FETCH_CLIENTS })
    api.get("client").then((result) => {
        dispatch({ type: SET_CLIENTS, payload: result.data })
    })
}