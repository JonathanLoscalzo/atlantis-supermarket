import React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import { getClients } from '../index'
import Presentation from '../presentation/Presentation'
import Spinner from '../../../../components/loading/spinner'

class Page extends React.Component {
    componentWillMount(props) {
        this.props.getClients();
    }

    render() {
        return (
            <Spinner loading={this.props.loading}>
                <Presentation clients={this.props.clients} />
            </Spinner>
        )
    }
}

const mapStateToProps = (state) => {
    return ({
        loading: state.client.list.loading,
        clients: state.client.list.clients
    })
}

const mapDispatchToProps = (dispatch) => ({
    ...bindActionCreators({ getClients }, dispatch)
})

export default connect(mapStateToProps, mapDispatchToProps)(Page)


