import { BaseEntity } from './../../shared';

export class Secao implements BaseEntity {
    constructor(
        public id?: number,
        public codigo?: string,
        public transmissao?: BaseEntity,
        public statusContagem?: BaseEntity,
    ) {
    }
}
