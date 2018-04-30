import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { LogAlteracaoContagem } from './log-alteracao-contagem.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<LogAlteracaoContagem>;

@Injectable()
export class LogAlteracaoContagemService {

    private resourceUrl =  SERVER_API_URL + 'api/log-alteracao-contagems';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(logAlteracaoContagem: LogAlteracaoContagem): Observable<EntityResponseType> {
        const copy = this.convert(logAlteracaoContagem);
        return this.http.post<LogAlteracaoContagem>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(logAlteracaoContagem: LogAlteracaoContagem): Observable<EntityResponseType> {
        const copy = this.convert(logAlteracaoContagem);
        return this.http.put<LogAlteracaoContagem>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<LogAlteracaoContagem>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<LogAlteracaoContagem[]>> {
        const options = createRequestOption(req);
        return this.http.get<LogAlteracaoContagem[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<LogAlteracaoContagem[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: LogAlteracaoContagem = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<LogAlteracaoContagem[]>): HttpResponse<LogAlteracaoContagem[]> {
        const jsonResponse: LogAlteracaoContagem[] = res.body;
        const body: LogAlteracaoContagem[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to LogAlteracaoContagem.
     */
    private convertItemFromServer(logAlteracaoContagem: LogAlteracaoContagem): LogAlteracaoContagem {
        const copy: LogAlteracaoContagem = Object.assign({}, logAlteracaoContagem);
        copy.timeStamp = this.dateUtils
            .convertDateTimeFromServer(logAlteracaoContagem.timeStamp);
        return copy;
    }

    /**
     * Convert a LogAlteracaoContagem to a JSON which can be sent to the server.
     */
    private convert(logAlteracaoContagem: LogAlteracaoContagem): LogAlteracaoContagem {
        const copy: LogAlteracaoContagem = Object.assign({}, logAlteracaoContagem);

        copy.timeStamp = this.dateUtils.toDate(logAlteracaoContagem.timeStamp);
        return copy;
    }
}
