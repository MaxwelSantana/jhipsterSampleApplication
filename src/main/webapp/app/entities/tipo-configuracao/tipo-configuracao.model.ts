import { BaseEntity } from './../../shared';

export class TipoConfiguracao implements BaseEntity {
    constructor(
        public id?: number,
        public descricao?: string,
    ) {
    }
}
