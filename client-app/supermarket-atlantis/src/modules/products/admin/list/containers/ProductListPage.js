import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { getProducts, onPageSizeChange, removeElementAt, goToCreate } from '../index'
import { Switch, Route } from 'react-router-dom'

import { Spinner } from '../../../../shared/index'
import ProductList from '../presentational/ProductList';
import ProductView from '../../view/container/Page'
import ProductCreate from '../../create/container/CreatePage'

const df = () => (<div></div>)

class ProductListPage extends React.Component {
    componentDidMount() {
        //this.props.get_products()
    }

    render() {

        let urls = {
            new: `${this.props.match.url}/new`,
            edit: `${this.props.match.url}/edit/:id`,
            remove: `${this.props.match.url}/remove/:id`,
            view: `${this.props.match.url}/view/:id`
        }

        return (
            <Spinner loading={this.props.loading}>
                <Switch>
                    <Route path={urls.new} component={ProductCreate} />
                    <Route path={urls.edit} component={df} />
                    <Route path={urls.view} component={ProductView} />
                    <ProductList
                        fetchData={this.props.getProducts}
                        urls={urls}
                        {...this.props} />
                </Switch>

                <Route path={urls.remove} component={df} />
            </Spinner>
        )
    }
}

const mapStateToProps = ({ product: element }) => {
    return ({
        loading: element.list.loading,
        defaultPageSize: element.list.defaultPageSize,
        data: element.list.data.rows,
        pages: element.list.data.pages,
        data_loading: element.list.data.loading,
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ getProducts, onPageSizeChange, removeElementAt, goToCreate }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ProductListPage)


