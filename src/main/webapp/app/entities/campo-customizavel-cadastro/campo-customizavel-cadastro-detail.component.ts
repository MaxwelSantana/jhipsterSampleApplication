import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CampoCustomizavelCadastro } from './campo-customizavel-cadastro.model';
import { CampoCustomizavelCadastroService } from './campo-customizavel-cadastro.service';

@Component({
    selector: 'jhi-campo-customizavel-cadastro-detail',
    templateUrl: './campo-customizavel-cadastro-detail.component.html'
})
export class CampoCustomizavelCadastroDetailComponent implements OnInit, OnDestroy {

    campoCustomizavelCadastro: CampoCustomizavelCadastro;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private campoCustomizavelCadastroService: CampoCustomizavelCadastroService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCampoCustomizavelCadastros();
    }

    load(id) {
        this.campoCustomizavelCadastroService.find(id)
            .subscribe((campoCustomizavelCadastroResponse: HttpResponse<CampoCustomizavelCadastro>) => {
                this.campoCustomizavelCadastro = campoCustomizavelCadastroResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCampoCustomizavelCadastros() {
        this.eventSubscriber = this.eventManager.subscribe(
            'campoCustomizavelCadastroListModification',
            (response) => this.load(this.campoCustomizavelCadastro.id)
        );
    }
}
