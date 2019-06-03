
const ACTION = "EXAMPLE/ACTION"

const initialState = {
    loading: false,
    product: {
        sku: "",
        upc: "",
        name: "",
        brand: "",
        minStock: 0,
        providerPrice: 0,
        retailPrice: 0,
        description: "",
        batchType: "DEFAULT",
        batches: null,
        provider: null, // debería ser un id
        categories: [] // deberían ser id's, u objetos nuevos {description}
    },
    batchTypes: ["DEFAULT", "EXPIRATION"],
    categories:[]
}

export default function reducer(state = initialState, action = {}) {

    switch (action.type) {
        case ACTION:
            return { ...state };
        default:
            return state;
    }
}

export const action = () => (dispatch) => {

}

export const save = (product) => (dispatch) => {

}