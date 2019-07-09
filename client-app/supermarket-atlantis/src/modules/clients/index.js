import { combineReducers } from 'redux'
import { isAdmin } from '../../common/auth'

import list from './list'
import view from './view'

let reducers = null

if (isAdmin()) {
    reducers = combineReducers({
        list,
        view
    })
}
else {
    reducers = combineReducers({})
}

export default reducers;