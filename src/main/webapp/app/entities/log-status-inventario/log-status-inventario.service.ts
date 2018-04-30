import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { LogStatusInventario } from './log-status-inventario.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<LogStatusInventario>;

@Injectable()
export class LogStatusInventarioService {

    private resourceUrl =  SERVER_API_URL + 'api/log-status-inventarios';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(logStatusInventario: LogStatusInventario): Observable<EntityResponseType> {
        const copy = this.convert(logStatusInventario);
        return this.http.post<LogStatusInventario>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(logStatusInventario: LogStatusInventario): Observable<EntityResponseType> {
        const copy = this.convert(logStatusInventario);
        return this.http.put<LogStatusInventario>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<LogStatusInventario>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<LogStatusInventario[]>> {
        const options = createRequestOption(req);
        return this.http.get<LogStatusInventario[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<LogStatusInventario[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: LogStatusInventario = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<LogStatusInventario[]>): HttpResponse<LogStatusInventario[]> {
        const jsonResponse: LogStatusInventario[] = res.body;
        const body: LogStatusInventario[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to LogStatusInventario.
     */
    private convertItemFromServer(logStatusInventario: LogStatusInventario): LogStatusInventario {
        const copy: LogStatusInventario = Object.assign({}, logStatusInventario);
        copy.timeStamp = this.dateUtils
            .convertDateTimeFromServer(logStatusInventario.timeStamp);
        return copy;
    }

    /**
     * Convert a LogStatusInventario to a JSON which can be sent to the server.
     */
    private convert(logStatusInventario: LogStatusInventario): LogStatusInventario {
        const copy: LogStatusInventario = Object.assign({}, logStatusInventario);

        copy.timeStamp = this.dateUtils.toDate(logStatusInventario.timeStamp);
        return copy;
    }
}
