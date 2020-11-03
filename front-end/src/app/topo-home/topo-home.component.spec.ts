import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopoHomeComponent } from './topo-home.component';

describe('TopoHomeComponent', () => {
  let component: TopoHomeComponent;
  let fixture: ComponentFixture<TopoHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopoHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopoHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
