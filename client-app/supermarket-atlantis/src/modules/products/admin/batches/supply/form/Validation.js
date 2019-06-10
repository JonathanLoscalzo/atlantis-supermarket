import * as yup from 'yup'
import { YupConfig } from '../../../../../shared'

const schema = yup
    .object()
    .shape({
        id: yup.string(),
        quantity: yup.number().positive().required(),
    })


export default schema;












