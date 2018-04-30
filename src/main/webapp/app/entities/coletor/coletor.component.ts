import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Coletor } from './coletor.model';
import { ColetorService } from './coletor.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-coletor',
    templateUrl: './coletor.component.html'
})
export class ColetorComponent implements OnInit, OnDestroy {
coletors: Coletor[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private coletorService: ColetorService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.coletorService.query().subscribe(
            (res: HttpResponse<Coletor[]>) => {
                this.coletors = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInColetors();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Coletor) {
        return item.id;
    }
    registerChangeInColetors() {
        this.eventSubscriber = this.eventManager.subscribe('coletorListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
