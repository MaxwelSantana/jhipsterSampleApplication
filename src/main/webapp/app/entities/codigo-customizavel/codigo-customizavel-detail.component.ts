import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CodigoCustomizavel } from './codigo-customizavel.model';
import { CodigoCustomizavelService } from './codigo-customizavel.service';

@Component({
    selector: 'jhi-codigo-customizavel-detail',
    templateUrl: './codigo-customizavel-detail.component.html'
})
export class CodigoCustomizavelDetailComponent implements OnInit, OnDestroy {

    codigoCustomizavel: CodigoCustomizavel;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private codigoCustomizavelService: CodigoCustomizavelService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCodigoCustomizavels();
    }

    load(id) {
        this.codigoCustomizavelService.find(id)
            .subscribe((codigoCustomizavelResponse: HttpResponse<CodigoCustomizavel>) => {
                this.codigoCustomizavel = codigoCustomizavelResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCodigoCustomizavels() {
        this.eventSubscriber = this.eventManager.subscribe(
            'codigoCustomizavelListModification',
            (response) => this.load(this.codigoCustomizavel.id)
        );
    }
}
