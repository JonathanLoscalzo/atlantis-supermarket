

import * as yup from 'yup';
// eslint-disable-next-line no-unused-vars
import { YupConfig } from '../../../shared/index'

export default yup.object()
    .shape({
        items: yup.array().min(1).required(), // no anda
        payment: yup.string().required(),
        pay: yup.number().moreThan(0).required()
    })