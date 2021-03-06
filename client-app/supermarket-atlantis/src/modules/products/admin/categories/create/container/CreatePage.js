import React from 'react'
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { reduxForm, formValueSelector } from 'redux-form'

import { load, create, goBack } from '../index';
import { Spinner } from '../../../../../shared'

import Form from '../../form/Form'
import schema from '../../form/Validation';
import { validator } from '../../../../../shared'

class CreatePage extends React.Component {

    componentWillMount() {
        this.props.load(this.props.match.params.id)
    }

    componentDidUpdate(prevProps) {
        if (this.props.pristine && !prevProps.pristine) {
            
        }
    }

    render() {
        return (
            <Spinner loading={this.props.loading}>
                <CreateForm
                    {...this.props}
                    title="Crear"
                    initialValues={this.props.element}
                    onSubmit={(values) => { this.props.create(values); }}
                />
            </Spinner>
        )
    }
}

const CreateForm = reduxForm({
    form: 'category/create',  // a unique identifier for this form
    validate: validator(schema),
    enableReinitialize: true
})(Form)

const selector = formValueSelector('category/update');

const mapStateToProps = ({ category: element, ...state }) => ({
    element: element.create.element,
    loading: element.create.loading,
    error: element.create.error,
})

const mapDispatchToProps = (dispatch) => bindActionCreators(
    {
        load,
        create,
        goBack,
    }, dispatch)

export default connect(mapStateToProps, mapDispatchToProps)(CreatePage)