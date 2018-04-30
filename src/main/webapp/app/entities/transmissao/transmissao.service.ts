import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Transmissao } from './transmissao.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Transmissao>;

@Injectable()
export class TransmissaoService {

    private resourceUrl =  SERVER_API_URL + 'api/transmissaos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(transmissao: Transmissao): Observable<EntityResponseType> {
        const copy = this.convert(transmissao);
        return this.http.post<Transmissao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(transmissao: Transmissao): Observable<EntityResponseType> {
        const copy = this.convert(transmissao);
        return this.http.put<Transmissao>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Transmissao>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Transmissao[]>> {
        const options = createRequestOption(req);
        return this.http.get<Transmissao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Transmissao[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Transmissao = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Transmissao[]>): HttpResponse<Transmissao[]> {
        const jsonResponse: Transmissao[] = res.body;
        const body: Transmissao[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Transmissao.
     */
    private convertItemFromServer(transmissao: Transmissao): Transmissao {
        const copy: Transmissao = Object.assign({}, transmissao);
        copy.timeStamp = this.dateUtils
            .convertDateTimeFromServer(transmissao.timeStamp);
        return copy;
    }

    /**
     * Convert a Transmissao to a JSON which can be sent to the server.
     */
    private convert(transmissao: Transmissao): Transmissao {
        const copy: Transmissao = Object.assign({}, transmissao);

        copy.timeStamp = this.dateUtils.toDate(transmissao.timeStamp);
        return copy;
    }
}
