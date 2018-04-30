import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Contagem } from './contagem.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Contagem>;

@Injectable()
export class ContagemService {

    private resourceUrl =  SERVER_API_URL + 'api/contagems';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(contagem: Contagem): Observable<EntityResponseType> {
        const copy = this.convert(contagem);
        return this.http.post<Contagem>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(contagem: Contagem): Observable<EntityResponseType> {
        const copy = this.convert(contagem);
        return this.http.put<Contagem>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Contagem>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Contagem[]>> {
        const options = createRequestOption(req);
        return this.http.get<Contagem[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Contagem[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Contagem = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Contagem[]>): HttpResponse<Contagem[]> {
        const jsonResponse: Contagem[] = res.body;
        const body: Contagem[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Contagem.
     */
    private convertItemFromServer(contagem: Contagem): Contagem {
        const copy: Contagem = Object.assign({}, contagem);
        copy.timeStamp = this.dateUtils
            .convertDateTimeFromServer(contagem.timeStamp);
        return copy;
    }

    /**
     * Convert a Contagem to a JSON which can be sent to the server.
     */
    private convert(contagem: Contagem): Contagem {
        const copy: Contagem = Object.assign({}, contagem);

        copy.timeStamp = this.dateUtils.toDate(contagem.timeStamp);
        return copy;
    }
}
