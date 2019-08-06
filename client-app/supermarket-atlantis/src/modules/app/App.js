import React from 'react';
import { Route, Switch } from 'react-router';
import Layout from '../../components/Layout';
//import Home from './components/Home';
import { ToastContainer } from 'react-toastify';

import ClientPage from '../clients/list/container/Page';
// import ClientEdit from './modules/clients/container/ClientEdit';
// import ClientNew from './modules/clients/container/ClientNew';
// import ClientView from './modules/clients/container/ClientView';
import HomePage from '../home/container/HomePage';
import LoginPage from '../auth/containers/LoginPage';
import ProductPage from '../products/admin/list/containers/ProductListPage'
import ShoppingPage from '../products/client/search/container/Page';
import LogoutPage from '../auth/containers/LogoutPage';
import ProviderPage from '../providers/list/container/Page'
import CategoryPage from '../products/admin/categories/list/container/Page'
import BatchPage from '../products/admin/batches/list/containers/Page'
import CartPage from '../cart/list/container/Page'
import SalePage from 'modules/sales/list/container/Page';

import { isAdmin, isClient } from '../../common/auth'


const Private = (props) => {
    if (localStorage.getItem('JWT_LOGIN')) {
        return (<React.Fragment> {props.children} </React.Fragment>)
    } else {
        return (<React.Fragment><LoginPage /></React.Fragment>)
    }
}

const renderAdminRoutes = () => {
    if (isAdmin()) {
        return (
            <React.Fragment>
                <Route path="/product" component={ProductPage} />
                <Route path="/provider" component={ProviderPage} />
                <Route path="/category" component={CategoryPage} />
                <Route path="/batch" component={BatchPage} />
                <Route path="/clients" component={ClientPage} />
            </React.Fragment>
        )
    }
}

const renderClientRoutes = () => {
    if (isClient()) {
        return (
            <React.Fragment>
                <Route path="/shopping" component={ShoppingPage} />
                <Route path="/basket" component={CartPage} />
                <Route path="/sales" component={SalePage} />
            </React.Fragment>
        )
    }
}

export default (props) => (
    <div>
        <Private>
            <Layout {...props} >
                <Switch>
                    <Route exact path="/" component={HomePage} />
                    {renderAdminRoutes()}
                    {renderClientRoutes()}
                    <Route component={NoMatch} />
                </Switch>
                <Route exact path="/logout" component={LogoutPage} />
            </Layout>
            <ToastContainer autoClose={2000} />
        </Private>
    </div>
);

const NoMatch = ({ location }) => (
    <div>
        <h3>No match for <code>{location.pathname}</code></h3>
    </div>
)