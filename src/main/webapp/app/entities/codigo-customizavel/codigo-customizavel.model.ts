import { BaseEntity } from './../../shared';

export class CodigoCustomizavel implements BaseEntity {
    constructor(
        public id?: number,
        public descricao?: string,
        public codigo?: string,
        public caminhoJar?: string,
    ) {
    }
}
