import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CampoCustomizavelColeta } from './campo-customizavel-coleta.model';
import { CampoCustomizavelColetaService } from './campo-customizavel-coleta.service';

@Component({
    selector: 'jhi-campo-customizavel-coleta-detail',
    templateUrl: './campo-customizavel-coleta-detail.component.html'
})
export class CampoCustomizavelColetaDetailComponent implements OnInit, OnDestroy {

    campoCustomizavelColeta: CampoCustomizavelColeta;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private campoCustomizavelColetaService: CampoCustomizavelColetaService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCampoCustomizavelColetas();
    }

    load(id) {
        this.campoCustomizavelColetaService.find(id)
            .subscribe((campoCustomizavelColetaResponse: HttpResponse<CampoCustomizavelColeta>) => {
                this.campoCustomizavelColeta = campoCustomizavelColetaResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCampoCustomizavelColetas() {
        this.eventSubscriber = this.eventManager.subscribe(
            'campoCustomizavelColetaListModification',
            (response) => this.load(this.campoCustomizavelColeta.id)
        );
    }
}
