import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Secao } from './secao.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Secao>;

@Injectable()
export class SecaoService {

    private resourceUrl =  SERVER_API_URL + 'api/secaos';

    constructor(private http: HttpClient) { }

    create(secao: Secao): Observable<EntityResponseType> {
        const copy = this.convert(secao);
        return this.http.post<Secao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(secao: Secao): Observable<EntityResponseType> {
        const copy = this.convert(secao);
        return this.http.put<Secao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Secao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Secao[]>> {
        const options = createRequestOption(req);
        return this.http.get<Secao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Secao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Secao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Secao[]>): HttpResponse<Secao[]> {
        const jsonResponse: Secao[] = res.body;
        const body: Secao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Secao.
     */
    private convertItemFromServer(secao: Secao): Secao {
        const copy: Secao = Object.assign({}, secao);
        return copy;
    }

    /**
     * Convert a Secao to a JSON which can be sent to the server.
     */
    private convert(secao: Secao): Secao {
        const copy: Secao = Object.assign({}, secao);
        return copy;
    }
}
