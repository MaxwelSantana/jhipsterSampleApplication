import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Inventario } from './inventario.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Inventario>;

@Injectable()
export class InventarioService {

    private resourceUrl =  SERVER_API_URL + 'api/inventarios';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(inventario: Inventario): Observable<EntityResponseType> {
        const copy = this.convert(inventario);
        return this.http.post<Inventario>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(inventario: Inventario): Observable<EntityResponseType> {
        const copy = this.convert(inventario);
        return this.http.put<Inventario>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Inventario>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Inventario[]>> {
        const options = createRequestOption(req);
        return this.http.get<Inventario[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Inventario[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Inventario = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Inventario[]>): HttpResponse<Inventario[]> {
        const jsonResponse: Inventario[] = res.body;
        const body: Inventario[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Inventario.
     */
    private convertItemFromServer(inventario: Inventario): Inventario {
        const copy: Inventario = Object.assign({}, inventario);
        copy.dataInventario = this.dateUtils
            .convertLocalDateFromServer(inventario.dataInventario);
        return copy;
    }

    /**
     * Convert a Inventario to a JSON which can be sent to the server.
     */
    private convert(inventario: Inventario): Inventario {
        const copy: Inventario = Object.assign({}, inventario);
        copy.dataInventario = this.dateUtils
            .convertLocalDateToServer(inventario.dataInventario);
        return copy;
    }
}
