import { combineReducers } from 'redux'
import { isClient } from 'common/auth'

import list from './list'

let reducers = null
if (isClient()) {
    reducers = combineReducers({
        list
    })
}
else {
    reducers = combineReducers({})
}

export default reducers;