import { BaseEntity } from './../../shared';

export class Contagem implements BaseEntity {
    constructor(
        public id?: number,
        public codigoSecao?: string,
        public codigoInterno?: string,
        public ean?: string,
        public quantidade?: number,
        public quantidadeOriginal?: number,
        public timeStamp?: any,
        public stringCustomizado1?: string,
        public stringCustomizado2?: string,
        public stringCustomizado3?: string,
        public stringCustomizado4?: string,
        public stringCustomizado5?: string,
        public stringCustomizado6?: string,
        public stringCustomizado7?: string,
        public stringCustomizado8?: string,
        public stringCustomizado9?: string,
        public stringCustomizado10?: string,
        public intCustomizado1?: number,
        public intCustomizado2?: number,
        public intCustomizado3?: number,
        public intCustomizado4?: number,
        public intCustomizado5?: number,
        public intCustomizado6?: number,
        public intCustomizado7?: number,
        public intCustomizado8?: number,
        public intCustomizado9?: number,
        public intCustomizado10?: number,
        public binCustomizado1?: boolean,
        public binCustomizado2?: boolean,
        public binCustomizado3?: boolean,
        public secao?: BaseEntity,
        public statusContagem?: BaseEntity,
        public motivoAlteracao?: BaseEntity,
    ) {
        this.binCustomizado1 = false;
        this.binCustomizado2 = false;
        this.binCustomizado3 = false;
    }
}
