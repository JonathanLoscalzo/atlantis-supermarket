import { set } from "lodash";
import * as Joi from 'joi'

const Validator = schema => values => {
    const formErrors = {};
    const result = Joi.validate(values, schema, { abortEarly: false });
    if (result.error === null) {
        return {};
    }
    result.error.details.forEach((error) => {
        set(formErrors, error.path, error.message);
    });
    return formErrors;
}

export default Validator;