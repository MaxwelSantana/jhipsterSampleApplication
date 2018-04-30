import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CampoCustomizavelCadastro } from './campo-customizavel-cadastro.model';
import { CampoCustomizavelCadastroService } from './campo-customizavel-cadastro.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-campo-customizavel-cadastro',
    templateUrl: './campo-customizavel-cadastro.component.html'
})
export class CampoCustomizavelCadastroComponent implements OnInit, OnDestroy {
campoCustomizavelCadastros: CampoCustomizavelCadastro[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private campoCustomizavelCadastroService: CampoCustomizavelCadastroService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.campoCustomizavelCadastroService.query().subscribe(
            (res: HttpResponse<CampoCustomizavelCadastro[]>) => {
                this.campoCustomizavelCadastros = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCampoCustomizavelCadastros();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CampoCustomizavelCadastro) {
        return item.id;
    }
    registerChangeInCampoCustomizavelCadastros() {
        this.eventSubscriber = this.eventManager.subscribe('campoCustomizavelCadastroListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
