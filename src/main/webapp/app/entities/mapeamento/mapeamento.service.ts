import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Mapeamento } from './mapeamento.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Mapeamento>;

@Injectable()
export class MapeamentoService {

    private resourceUrl =  SERVER_API_URL + 'api/mapeamentos';

    constructor(private http: HttpClient) { }

    create(mapeamento: Mapeamento): Observable<EntityResponseType> {
        const copy = this.convert(mapeamento);
        return this.http.post<Mapeamento>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(mapeamento: Mapeamento): Observable<EntityResponseType> {
        const copy = this.convert(mapeamento);
        return this.http.put<Mapeamento>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Mapeamento>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Mapeamento[]>> {
        const options = createRequestOption(req);
        return this.http.get<Mapeamento[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Mapeamento[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Mapeamento = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Mapeamento[]>): HttpResponse<Mapeamento[]> {
        const jsonResponse: Mapeamento[] = res.body;
        const body: Mapeamento[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Mapeamento.
     */
    private convertItemFromServer(mapeamento: Mapeamento): Mapeamento {
        const copy: Mapeamento = Object.assign({}, mapeamento);
        return copy;
    }

    /**
     * Convert a Mapeamento to a JSON which can be sent to the server.
     */
    private convert(mapeamento: Mapeamento): Mapeamento {
        const copy: Mapeamento = Object.assign({}, mapeamento);
        return copy;
    }
}
