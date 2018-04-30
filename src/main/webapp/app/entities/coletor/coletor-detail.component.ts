import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Coletor } from './coletor.model';
import { ColetorService } from './coletor.service';

@Component({
    selector: 'jhi-coletor-detail',
    templateUrl: './coletor-detail.component.html'
})
export class ColetorDetailComponent implements OnInit, OnDestroy {

    coletor: Coletor;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private coletorService: ColetorService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInColetors();
    }

    load(id) {
        this.coletorService.find(id)
            .subscribe((coletorResponse: HttpResponse<Coletor>) => {
                this.coletor = coletorResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInColetors() {
        this.eventSubscriber = this.eventManager.subscribe(
            'coletorListModification',
            (response) => this.load(this.coletor.id)
        );
    }
}
