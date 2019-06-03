import api from "../../../../../common/api";

const FETCHED_PROVIDERS = "PROVIDERS/FETCHED"
const FETCH_PROVIDERS = "PROVIDERS/FETCH"
const PAGE_CHANGE = "PROVIDERS/CHANGE_PAGE"

const initialState = {
    loading: false,
    data: {
        rows: [],
        pages: null,
        loading: false
    },
    defaultPageSize: 5
}

export default function reducer(state = initialState, action = {}) {

    switch (action.type) {
        case FETCH_PROVIDERS:
            return {
                ...state,
                data: {
                    ...state.data,
                    loading: true
                }
            }
        case FETCHED_PROVIDERS:
            return {
                ...state,
                data: {
                    rows: action.payload.content,
                    loading: false,
                    pages: action.payload.totalPages
                }
            }
        case PAGE_CHANGE:
            return {
                ...state,
                defaultPageSize: action.payload
            }
        default:
            return state;
    }
}

export const getProviders = ({ page, sorted: sort, pageSize: size, ...others } = { page: 0, sort: "", size: 10 }) => (dispatch) => {
    dispatch({ type: FETCH_PROVIDERS, payload: {} })

    let params = new URLSearchParams();
    sort.forEach(s => params.append("sort", `${s.id},${s.desc ? "desc" : "asc"}`))
    params.append("page", page);
    params.append("size", size);

    api.get("/provider", { params: params })
        .then((result) => {
            dispatch({
                type: FETCHED_PROVIDERS,
                payload:
                {
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