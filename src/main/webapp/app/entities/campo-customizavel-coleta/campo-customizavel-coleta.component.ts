import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CampoCustomizavelColeta } from './campo-customizavel-coleta.model';
import { CampoCustomizavelColetaService } from './campo-customizavel-coleta.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-campo-customizavel-coleta',
    templateUrl: './campo-customizavel-coleta.component.html'
})
export class CampoCustomizavelColetaComponent implements OnInit, OnDestroy {
campoCustomizavelColetas: CampoCustomizavelColeta[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private campoCustomizavelColetaService: CampoCustomizavelColetaService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.campoCustomizavelColetaService.query().subscribe(
            (res: HttpResponse<CampoCustomizavelColeta[]>) => {
                this.campoCustomizavelColetas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCampoCustomizavelColetas();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CampoCustomizavelColeta) {
        return item.id;
    }
    registerChangeInCampoCustomizavelColetas() {
        this.eventSubscriber = this.eventManager.subscribe('campoCustomizavelColetaListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
