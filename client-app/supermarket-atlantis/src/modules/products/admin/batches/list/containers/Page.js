import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import {
    getBatches, onPageSizeChange,
    removeElementAt, goToCreate,
    handlePivot
} from '../index'
import { Switch, Route } from 'react-router-dom'

import { Spinner } from '../../../../../shared/index'
import BatchList from '../presentational/ProductList';
import BatchView from '../../view/container/Page'
import BatchCreate from '../../create/container/CreatePage'
import BatchUpdateView from '../../update/container/ElementUpdatePage'
import BatchSupplyView from '../../supply/container/Page'

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
            view: `${this.props.match.url}/view/:id`,
            supply: `${this.props.match.url}/supply/:id`
        }

        return (
            <Spinner loading={this.props.loading}>
                <Switch>
                    <Route path={urls.new} component={BatchCreate} />
                    <Route path={urls.edit} component={BatchUpdateView} />
                    <Route path={urls.view} component={BatchView} />
                    <BatchList
                        fetchData={this.props.getBatches}
                        urls={urls}
                        {...this.props} />
                </Switch>

                <Route path={urls.supply} component={BatchSupplyView} />
                <Route path={urls.remove} component={df} />
            </Spinner>
        )
    }
}

const mapStateToProps = ({ batch: element }) => {
    return ({
        loading: element.list.loading,
        defaultPageSize: element.list.defaultPageSize,
        data: element.list.data.rows,
        pages: element.list.data.pages,
        data_loading: element.list.data.loading,
        pivotBy: element.list.pivotBy,
        checked: element.list.checked
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({
        getBatches,
        onPageSizeChange, removeElementAt, goToCreate,
        handlePivot
    }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(ProductListPage)


