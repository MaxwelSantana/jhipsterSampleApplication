import { BaseEntity } from './../../shared';

export class Cliente implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public sigla?: string,
        public inventarios?: BaseEntity[],
    ) {
    }
}
