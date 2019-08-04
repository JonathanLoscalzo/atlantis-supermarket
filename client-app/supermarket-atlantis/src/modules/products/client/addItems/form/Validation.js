import * as yup from 'yup'
import { YupConfig } from 'modules/shared'

const schema = yup
    .object()
    .shape({
        //product: yup.object(),
        quantity: yup.number().positive().required(),
    })


export default schema;












