// @ts-ignore
import _ from 'lodash'
import { api } from '../../../shared'
import { addItemToBasket } from '../../../cart/list/index'
const FETCHED = "PRODUCT/CLIENT/FETCHED"
const FETCH = "PRODUCT/CLIENT/FETCH"
const SEARCH = "PRODUCT/CLIENT/SEARCH"
const PAGE_CHANGE = "PRODUCT/CLIENT/CHANGE_PAGE"
const REMOVE_ELEMENT_AT = "PRODUCT/CLIENT/LIST/REMOVE_AT"
const ADVANCE_SEARCH = "PRODUCT/CLIENT/ADVANCE_SEARCH"

const initialState = {
    // initial value for search-form
    formSearch: {
        name: '',
        advanceSearch: false,
        minPrice: null,
        maxPrice: null
    },
    lastSearch: {
        name: '',
        minPrice: null,
        maxPrice: null
    },
    loading: false,
    data: {
        rows: [],
        pages: null,
        loading: false, //LOADING DE LA TABLA
        page: 0,
        sort: [],
    },
    defaultPageSize: 5
}

const handlers = {
    [FETCH]: handleFetchProducts,
    [FETCHED]: handleFetchedProducts,
    [PAGE_CHANGE]: handlePageChange,
    [REMOVE_ELEMENT_AT]: handleRemoveElementAt,
    [SEARCH]: handleSearch,
    [ADVANCE_SEARCH]: handleAdvanceSearch
}

export const getProducts = (
    // @ts-ignore
    { page, sorted: sort, pageSize: size, ...others } = { page: 0, sort: [], size: 10 }) =>
    (dispatch, getState) => {
        dispatch({ type: FETCH, payload: {} })

        let params = new URLSearchParams();
        sort.forEach(s => params.append("sort", `${s.id},${s.desc ? "desc" : "asc"}`))
        // @ts-ignore
        params.append("page", page);
        params.append("size", size);

        let body = getState().product.search.lastSearch;

        api.post(`/product/search?${params}`, body)
            .then((result) => {
                dispatch({
                    type: FETCHED,
                    payload:
                    {
                        page: page,
                        sort: sort,
                        size: size,
                        content: result.data.content,
                        totalPages: result.data.totalPages
                    }
                })
            }).catch(() => {

            })
    }

export const search = ({ ...values }) =>
    (dispatch, getState) => {

        let page = 0, sort = [], size = getState().product.search.defaultPageSize;

        dispatch({ type: SEARCH, payload: values })

        let params = new URLSearchParams();
        sort.forEach(s => params.append("sort", `${s.id},${s.desc ? "desc" : "asc"}`))

        // @ts-ignore
        params.append("page", page);
        params.append("size", size);

        let body = { ...values }

        api.post(`/product/search?${params}`, body)
            .then((result) => {
                dispatch({
                    type: FETCHED,
                    payload:
                    {
                        page: page,
                        sort: sort,
                        size: size,
                        content: result.data.content,
                        totalPages: result.data.totalPages
                    }
                })
            }).catch(() => {

            })
    }

export const onPageSizeChange = (others) => (dispatch) => {
    dispatch({ type: PAGE_CHANGE, payload: others })
}

export const removeElementAt = (id) => (dispatch, state) => {
    dispatch(getProducts(
        // @ts-ignore
        {
            page: state().product.list.data.page,
            sorted: state().product.list.data.sort,
            pageSize: state().product.list.defaultPageSize
        }))
}

export const enableAdvanceSearch = () => (dispatch) => {
    dispatch({ type: ADVANCE_SEARCH })
}

export const addItemsToBasket = (product, quantity = 1) => dispatch => {
    dispatch(addItemToBasket(product, quantity))
}


export default function handler(state = initialState, action = {}) {
    const handler = handlers[action.type];
    return handler ? handler(state, action) : state;
}

function handleAdvanceSearch(state) {
    return {
        ...state,
        formSearch: {
            ...state.formSearch,
            advanceSearch: !state.formSearch.advanceSearch
        }
    };
}

function handleSearch(state, action) {
    return {
        ...state,
        lastSearch: action.payload
    };
}

function handleRemoveElementAt(state, action) {
    let filtered = _.filter(state.data.rows, x => x.id != action.payload);
    return {
        ...state,
        data: {
            ...state.data,
            rows: filtered
        }
    };
}

function handlePageChange(state, action) {
    return {
        ...state,
        defaultPageSize: action.payload
    };
}

function handleFetchedProducts(state, action) {
    return {
        ...state,
        data: {
            rows: action.payload.content,
            loading: false,
            pages: action.payload.totalPages,
            page: action.payload.page,
            sort: action.payload.sort,
            size: action.payload.size,
        }
    };
}

function handleFetchProducts(state) {
    return {
        ...state,
        data: {
            ...state.data,
            loading: true
        }
    };
}
