
import _ from 'lodash'
import { replace } from 'connected-react-router';
import { api } from '../../../../shared'
import moment from 'moment';

const FETCHED = "BATCH/ADMIN/FETCHED"
const FETCH = "BATCH/ADMIN/FETCH"
const PAGE_CHANGE = "BATCH/ADMIN/CHANGE_PAGE"
const REMOVE_ELEMENT_AT = "BATCH/ADMIN/LIST/REMOVE_AT"
const CHECK_CHANGE = "BATCH/ADMIN/CHECK_CHANGE"

const initialState = {
    loading: false,
    data: {
        rows: [],
        pages: null,
        loading: false, //LOADING DE LA TABLA
        page: 0,
        sort: [],
    },
    defaultPageSize: 5,
    pivotBy: [],
    checked: false
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
                    rows: action.payload.content.map(x => {

                        let batches = x.batches.map(b => ({
                            ...b,
                            expiration: moment(b.expiration),
                            isExpired: moment(b.expiration) <= moment().startOf('day')
                        }));

                        let stock = _.sum(batches.filter(b => !b.isExpired).map(b => b.remainingUnits))

                        return {
                            ...x,
                            batches,
                            stock,
                            isLowStock: x.minStock > stock
                        }
                    }),
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
        case CHECK_CHANGE:
            return {
                ...state,
                checked: !state.checked,
                pivotBy: state.checked ? [] : ["type"]
            }
        default:
            return state;
    }
}

export const getBatches = (
    { page, sorted: sort, pageSize: size, ...others } =
        { page: 0, sort: [], size: 10 }) =>
    (dispatch) => {
        dispatch({ type: FETCH, payload: {} })

        let params = new URLSearchParams();
        sort.forEach(s => params.append("sort", `${s.id},${s.desc ? "desc" : "asc"}`))
        params.append("page", page);
        params.append("size", size);

        api.get("/product/batch", { params: params })
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
    dispatch(getBatches({
        page: state().batch.list.data.page,
        sorted: state().batch.list.data.sort,
        pageSize: state().batch.list.defaultPageSize
    }))
}

export const goToCreate = () => dispatch => {
    dispatch(replace("/batch/new"))
}

export const handlePivot = ({ value, ...props }) => dispatch => {
    dispatch({ type: CHECK_CHANGE })
}