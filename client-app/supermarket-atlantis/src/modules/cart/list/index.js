import _ from 'lodash'


const ADD_BASKET = "BASKET/ADD";

const initialState = {
    items: [],

}

const handleAddBasket = (state, action) => {
    let elem = _.find(state.items, (f) => f.product.id === action.payload.product.id)
    if (elem) {
        debugger
        elem.quantity += action.payload.quantity
        return {
            ...state,
            items: [
                ..._.filter(state.items, state.items, (f) => f.product.id !== action.payload.product.id)
                , elem
            ]
        }
    } else {
        return {
            ...state,
            items: [...state.items, action.payload]
        }
    }

}

const handlers = {
    [ADD_BASKET]: handleAddBasket
}

export default function handler(state = initialState, action = {}) {
    const handler = handlers[action.type];
    return handler ? handler(state, action) : state;
}

export function addItemToBasket(product, quantity) {
    return {
        type: ADD_BASKET,
        payload: {
            product: product,
            quantity: quantity
        }
    }
}





