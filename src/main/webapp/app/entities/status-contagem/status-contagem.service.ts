import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { StatusContagem } from './status-contagem.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<StatusContagem>;

@Injectable()
export class StatusContagemService {

    private resourceUrl =  SERVER_API_URL + 'api/status-contagems';

    constructor(private http: HttpClient) { }

    create(statusContagem: StatusContagem): Observable<EntityResponseType> {
        const copy = this.convert(statusContagem);
        return this.http.post<StatusContagem>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(statusContagem: StatusContagem): Observable<EntityResponseType> {
        const copy = this.convert(statusContagem);
        return this.http.put<StatusContagem>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<StatusContagem>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<StatusContagem[]>> {
        const options = createRequestOption(req);
        return this.http.get<StatusContagem[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<StatusContagem[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: StatusContagem = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<StatusContagem[]>): HttpResponse<StatusContagem[]> {
        const jsonResponse: StatusContagem[] = res.body;
        const body: StatusContagem[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to StatusContagem.
     */
    private convertItemFromServer(statusContagem: StatusContagem): StatusContagem {
        const copy: StatusContagem = Object.assign({}, statusContagem);
        return copy;
    }

    /**
     * Convert a StatusContagem to a JSON which can be sent to the server.
     */
    private convert(statusContagem: StatusContagem): StatusContagem {
        const copy: StatusContagem = Object.assign({}, statusContagem);
        return copy;
    }
}
