import * as yup from 'yup'
import { YupConfig } from 'modules/shared'

const schema = yup
    .object()
    .shape({
        product: yup.object(),
        quantity: yup.number().max(yup.ref("maxUnits")).positive().required(),
    })


export default schema;












