import { applyMiddleware, combineReducers, compose, createStore } from 'redux';
import thunk from 'redux-thunk';
import { connectRouter } from 'connected-react-router'
import { routerMiddleware } from 'connected-react-router'
import { reducer as formReducer } from 'redux-form'
import { composeWithDevTools } from 'remote-redux-devtools'

import client from '../modules/clients';
import auth from '../modules/auth'
import product from '../modules/products'
import home from '../modules/home'
import provider from '../modules/providers'
import category from '../modules/products/admin/categories'
import batch from '../modules/products/admin/batches'


export default function configureStore(history, initialState) {
    const reducers = {
        form: formReducer,
        router: connectRouter(history),
        auth,
        product,
        home,
        client,
        provider,
        category,
        batch,
        // client,
        // element, 
        // task,
        // repair
    };

    const middleware = [
        thunk,
        routerMiddleware(history)
    ];

    const enhancers = [];
    const isDevelopment = process.env.NODE_ENV === 'development';

    if (isDevelopment && typeof window !== 'undefined' && window.devToolsExtension) {
        enhancers.push(window.devToolsExtension());
    }

    const rootReducer = combineReducers({
        ...reducers
    });

    return createStore(
        rootReducer,
        initialState,
        compose(applyMiddleware(...middleware), ...enhancers)
    );
}