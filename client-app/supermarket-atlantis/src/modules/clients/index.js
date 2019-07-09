import { combineReducers } from 'redux'
import { isAdmin } from '../../common/auth'

import list from './list'

let reducers = null

if (isAdmin()) {
    reducers = combineReducers({
        list
    })
}
else {
    reducers = combineReducers({})
}

export default reducers;