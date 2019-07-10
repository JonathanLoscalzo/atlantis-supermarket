import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { reduxForm } from 'redux-form'

import { getProducts, onPageSizeChange, search, enableAdvanceSearch } from '../index'
import { Switch, Route } from 'react-router-dom'

import Form from '../presentation/Form'
import { Spinner } from '../../../../shared/index'
import ProductList from '../presentation/ProductList';
import ProductView from '../../view/container/Page'
import { validator } from '../../../../shared'
import schema from '../schema'

const df = () => (<div></div>)

class ProductListPage extends React.Component {
    componentDidMount() {
        //this.props.get_products()
    }

    render() {

        let urls = {
            view: `${this.props.match.url}/view/:id`
        }

        return (
            <Spinner loading={this.props.loading}>
                <SearchFrom
                    title="Búsqueda"
                    initialValues={this.props.element}
                    enableAdvanceSearch={this.props.enableAdvanceSearch}
                    onSubmit={(values) => { this.props.search(values); }}
                />
                <hr />
                <Switch>
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
        element: element.search.formSearch,
        loading: element.search.loading,
        defaultPageSize: element.search.defaultPageSize,
        data: element.search.data.rows,
        pages: element.search.data.pages,
        data_loading: element.search.data.loading,
    })
}

const SearchFrom = reduxForm({
    form: 'products/search',  // a unique identifier for this form
    validate: validator(schema),
    enableReinitialize: true
})(Form)

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({
        getProducts,
        onPageSizeChange,
        search,
        enableAdvanceSearch
    }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ProductListPage)


