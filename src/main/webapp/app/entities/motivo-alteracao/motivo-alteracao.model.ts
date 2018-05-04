import { BaseEntity } from './../../shared';

export class MotivoAlteracao implements BaseEntity {
    constructor(
        public id?: number,
        public descricao?: string,
        public contagens?: BaseEntity[],
    ) {
    }
}
