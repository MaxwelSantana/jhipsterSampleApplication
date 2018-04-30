import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CodigoCustomizavel } from './codigo-customizavel.model';
import { CodigoCustomizavelService } from './codigo-customizavel.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-codigo-customizavel',
    templateUrl: './codigo-customizavel.component.html'
})
export class CodigoCustomizavelComponent implements OnInit, OnDestroy {
codigoCustomizavels: CodigoCustomizavel[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private codigoCustomizavelService: CodigoCustomizavelService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.codigoCustomizavelService.query().subscribe(
            (res: HttpResponse<CodigoCustomizavel[]>) => {
                this.codigoCustomizavels = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCodigoCustomizavels();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CodigoCustomizavel) {
        return item.id;
    }
    registerChangeInCodigoCustomizavels() {
        this.eventSubscriber = this.eventManager.subscribe('codigoCustomizavelListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
