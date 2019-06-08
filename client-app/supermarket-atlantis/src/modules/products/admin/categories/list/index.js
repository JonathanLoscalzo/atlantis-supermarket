import _ from 'lodash'
import { replace } from 'connected-react-router';
import { api } from '../../../../shared'

const FETCHED = "CATEGORY/FETCHED"
const FETCH = "CATEGORY/FETCH"
const PAGE_CHANGE = "CATEGORY/CHANGE_PAGE"
const REMOVE_ELEMENT_AT = "CATEGORY/LIST/REMOVE_AT"

const initialState = {
    loading: false,
    data: {
        rows: [],
        pages: null,
        loading: false,
        page: 0,
        sort: [],
    },
    defaultPageSize: 5
}

export default function reducer(state = initialState, action = {}) {

    switch (action.type) {
        case FETCH:
            return {
                ...state,
                data: {
                    ...state.data,
                    loading: true
                }
            }
        case FETCHED:
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
            }
        case PAGE_CHANGE:
            return {
                ...state,
                defaultPageSize: action.payload
            }

        case REMOVE_ELEMENT_AT:
            let filtered = _.filter(state.data.rows, x => x.id != action.payload)
            return {
                ...state,
                data: {
                    ...state.data,
                    rows: filtered
                }
            }

        default:
            return state;
    }
}

export const getProviders = ({ page, sorted: sort, pageSize: size, ...others } = { page: 0, sort: [], size: 10 }) => (dispatch) => {
    dispatch({ type: FETCH, payload: {} })

    let params = new URLSearchParams();
    sort.forEach(s => params.append("sort", `${s.id},${s.desc ? "desc" : "asc"}`))
    params.append("page", page);
    params.append("size", size);

    api.get("/product/category", { params: params })
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
    dispatch(getProviders({
        page: state().provider.list.data.page,
        sorted: state().provider.list.data.sort,
        pageSize: state().provider.list.defaultPageSize
    }))
}

export const goToCreate = () => dispatch => {
    dispatch(replace("/category/new"))
}