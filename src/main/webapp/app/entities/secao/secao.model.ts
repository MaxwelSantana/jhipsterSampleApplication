import { BaseEntity } from './../../shared';

export class Secao implements BaseEntity {
    constructor(
        public id?: number,
        public codigo?: string,
        public statusContagem?: BaseEntity,
        public transmissao?: BaseEntity,
        public contagens?: BaseEntity[],
    ) {
    }
}
