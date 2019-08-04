// @ts-ignore
import _ from 'lodash'

const ADD_BASKET = "BASKET/ADD";
const DELETE_ITEM_BASKET = "BASKET/DELETE";

const initialState = {
    loading: false,
    items: [],
}

const handleAddBasket = (state, action) => {
    let elem = _.find(state.items, (f) => f.product.id === action.payload.product.id)
    if (elem) {
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

const handleDeleteItem = (state, action) => {
    return {
        ...state,
        items: [
            ..._.filter(state.items, (f) => f.product.id !== action.payload.product.id)
        ]
    }
}

const handlers = {
    [ADD_BASKET]: handleAddBasket,
    [DELETE_ITEM_BASKET]: handleDeleteItem
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

export function deleteItem(product) {
    return {
        type: DELETE_ITEM_BASKET,
        payload: {
            product
        }
    }
}

export function getItemSelector(state, id) {
    return state.cart.list.items.find(x => x.product.id === id)
}





