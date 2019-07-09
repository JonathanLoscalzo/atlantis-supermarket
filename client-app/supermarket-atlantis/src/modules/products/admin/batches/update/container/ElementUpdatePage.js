import React from 'react'
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { reduxForm, formValueSelector } from 'redux-form'

import { load, update, goBack } from '../index';

import { Spinner, validator } from '../../../../../shared'

import Form from '../../form/Form'
import schema from '../../form/Validation';

class EditPage extends React.Component {

    componentWillMount() {
        //TODO: este id es el del batch
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
                    mode="UPDATE"
                    title="Editar Batch"
                    initialValues={this.props.element}
                    onSubmit={(values) => { this.props.update(values); }}
                />
            </Spinner>
        )
    }
}

const CreateForm = reduxForm({
    form: 'batch/update',  // a unique identifier for this form
    validate: validator(schema()),
    enableReinitialize: true
})(Form)

const selector = formValueSelector('batch/update');

const mapStateToProps = ({ batch: element, ...state }) => ({
    element: element.update.element,
    loading: element.update.loading,
    error: element.update.error,
})

const mapDispatchToProps = (dispatch) => bindActionCreators(
    {
        load,
        update,
        goBack,
    }, dispatch)

export default connect(mapStateToProps, mapDispatchToProps)(EditPage)